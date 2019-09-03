package com.example.demo.service.impl;

import com.example.demo.domain.Administrator;
import com.example.demo.repository.AdministratorRepository;
import com.example.demo.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorImpl implements AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    //更新密码和电话
    @Override
    public String updateA(String pass,String phone,Integer aid) {
        boolean flag=false;
        try{
            Administrator ad=administratorRepository.findByAId(aid);
            if(ad!=null){
                administratorRepository.updateA(pass,phone,aid);
                flag=true;
            }
            else{
                return "null";
            }

        }
        catch(Exception e){
            System.out.println(e.toString());

        }
        if(flag){
            return "true";//成功
        }
        else{
            return "false";//失败
        }


    }

    //注册
    @Override
    public String CreateA(Administrator administrator){
        boolean flag=false;
        try{
            administratorRepository.save(administrator);
            flag=true;
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        if(flag){return "true";}//成功
        else{return "false";}

    }

    //删除
    @Override
    public String DeleteA(Integer aId){
        boolean flag=false;
        try{

            Administrator administrator=administratorRepository.findByAId(aId);
            if(administrator!=null){
                administratorRepository.delete(aId);
                flag=true;
            }
            else{
                return "null";
            }


        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        if(flag){
            return "true";
        }
        else{
            return "false";
        }

    }

    //显示除密码外全部
    @Override
    public List<Administrator> findAll(){
        return administratorRepository.findAll();

    }

    @Override
    public String CheckA(Integer aId, String aPass) {
        Administrator administrator=administratorRepository.findByAId(aId);
        if(administrator==null){
            return "null";
        }
        if(administrator.getaPass().equals(aPass)){
            return "true";
        }
        return "false";
    }
}
