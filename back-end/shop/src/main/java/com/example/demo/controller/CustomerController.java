package com.example.demo.controller;

import com.example.demo.domain.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/Customer",produces = {"application/json;charset=UTF-8"})
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    Customer thisCustomer=new Customer();

    @PostMapping("/CreateC")
    public String CreateC(@RequestBody Customer customer){
        return customerService.CreateC(customer);
    }
    @GetMapping("/DeleteC/cId={cId}")
    public String DeleteC(@PathVariable("cId") Integer cId){
        return customerService.DeleteC(cId);
    }

    @GetMapping("/findByCId/cId={cId}")
    public Customer findByCId(@PathVariable("cId") Integer cId){
        return customerService.findByCId(cId);
    }

    @GetMapping("/CheckC/cName={cName}cPass={cPass}")//登录
    public String CheckC(@PathVariable("cName") String cName,//HttpServletRequest request将当前用户保存到Session中
                         @PathVariable("cPass") String cPass){
        //HttpSession session=request.getSession();
        Customer customer=customerService.CheckC(cName,cPass);
        if(customer!=null){
            thisCustomer=customer;
            return "true";
        }
        return "false";
    }

    @GetMapping("/getCurrentCustomer")//从Session中获取当前用户
    public Customer getCurrentCustomer(){//HttpServletRequest request

        /*HttpSession session=request.getSession();
        Customer customer=(Customer) session.getAttribute("CurrentCustomer");

        if(customer==null){
            System.out.println("false");
        }
        session.setAttribute("CurrentCustomer",customer);*/
        return thisCustomer;

    }

    @GetMapping("/Logout")//注销
    public String Logout(){
        thisCustomer=null;
        return "true";
    }

    @GetMapping("/findByCNameLike/cName={cName}")
    public List<Customer> findByCNameLike(@PathVariable("cName") String cName){
        return customerService.findByCNameLike(cName);
    }

    @GetMapping("/findAll")
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    //更改密码
    @GetMapping("/UpdateCPassword/First_cPassword={First_cPassword}" +
            "Second_cPassword={Second_cPassword}" +
            "cId={cId}" +
            "Initial_cPassword={Initial_cPassword}")
    public String UpdateCPassword(@PathVariable("First_cPassword") String First_cPassword,
                                  @PathVariable("Second_cPassword") String Second_cPassword,
                                  @PathVariable("cId") Integer cId,
                                  @PathVariable("Initial_cPassword") String Initial_cPassword){
        return customerService.UpdateCPassword(First_cPassword,Second_cPassword,cId,Initial_cPassword);
    }
    //更改基本信息
    @GetMapping("/UpdateCustomer/cAddress={cAddress}cPhone={cPhone}cId={cId}")
    public String UpdateCustomer(@PathVariable("cAddress") String cAddress,
                                 @PathVariable("cPhone") String cPhone,
                                 @PathVariable("cId") Integer cId){
        return customerService.UpdateCustomer(cAddress,cPhone,cId);
    }
}
