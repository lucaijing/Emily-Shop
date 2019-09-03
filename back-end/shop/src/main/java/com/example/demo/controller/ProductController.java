package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/Product")//,produces = {"application/json;charset=UTF-8"})//multipart/form-data
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/CreateB")
    public String CreateB(@RequestBody Product product) {
        return productService.CreateB(product);
    }

    @GetMapping("/DeleteB/proId={proId}")
    public  String DeleteB(@PathVariable("proId") Integer proId){
        return productService.DeleteB(proId);
    }
    @GetMapping("/findAll")
    List<Product> findALL(){
        return productService.findALL();
    }

    @GetMapping("/UpdateB/proName={proName}proType={proType}proBraname={proBraname}proPrice={proPrice}proSkin={proSkin}proDescription={proDescription}proId={proId}")
    String UpdateB(@PathVariable("proName") String proName,
                   //@PathVariable("sId") Integer sId,
                   @PathVariable("proType") String proType,
                   @PathVariable("proBraname") String proBraname,
                   @PathVariable("proPrice") double proPrice,
                   @PathVariable("proSkin") String proSkin,
                   @PathVariable("proDescription") String proDescription,
                   @PathVariable("proId") Integer proId){
        return productService.UpdateB(proName,proType,proBraname,proPrice,proSkin,proDescription,proId);
    }

    @GetMapping("/UpdatePrice/proPrice={proPrice}proId={proId}")
    String UpdatePrice(@PathVariable("proPrice") double proPrice,
                       @PathVariable("proId") Integer proId){
        return productService.UpdatePrice(proPrice,proId);
    }
    @GetMapping("/UpdateDescription/proDescription={proDescription}proId={proId}")
    String UpdateDescription(@PathVariable("proDescription") String proDescription,
                             @PathVariable("proId") Integer proId){
        return productService.UpdateDescription(proDescription,proId);
    }
    @GetMapping("/UpdateNumber/proNumber={proNumber}proId={proId}")
    String UpdateNumber(@PathVariable("proNumber") Integer proNumber,
                        @PathVariable("proId") Integer proId){
            return productService.UpdateNumber(proNumber,proId);

    }

    @GetMapping("/findByProSenior/proBraname={proBraname}proSkin={proSkin}proType={proType}price1={price1}price2={price2}")
    List<Product> findByProSenior(@PathVariable("proBraname") String proBraname,
                                  @PathVariable("proSkin") String proSkin,
                                  @PathVariable("proType") String proType,
                                  @PathVariable("price1") double price1,
                                  @PathVariable("price2") double price2)
    {
        return productService.findByProSenior(proBraname,proSkin,proType,price1,price2);
    }

    @GetMapping("/findByProSeniorDesc/proBraname={proBraname}proSkin={proSkin}proType={proType}price1={price1}price2={price2}")
    List<Product> findByProSeniorDesc(@PathVariable("proBraname") String proBraname,
                                  @PathVariable("proSkin") String proSkin,
                                  @PathVariable("proType") String proType,
                                  @PathVariable("price1") double price1,
                                  @PathVariable("price2") double price2)
    {
        return productService.findByProSeniorDesc(proBraname,proSkin,proType,price1,price2);
    }

    @GetMapping("/findByProId/proId={proId}")
    Product findByProId(@PathVariable("proId") Integer proId){
        return productService.findByProId(proId);
    }


    @GetMapping("/findByproNameLike/proName={proName}")//化妆品名模糊
    List<Product> findByproNameLike(@PathVariable("proName") String proName){
        return productService.findByproNameLike(proName);
    }

    @GetMapping("/findByproNameLikeDesc/proName={proName}")//化妆品名模糊
    List<Product> findByproNameLikeDesc(@PathVariable("proName") String proName){
        return productService.findByproNameLikeDesc(proName);
    }

    @GetMapping("/findByproNameLikeAndProType/proName={proName}proType={proType}")//化妆品名模糊+类别
    List<Product> findByproNameLikeAndProType(@PathVariable String proName,
                                       @PathVariable String proType){
        return productService.findByproNameLikeAndProType(proName,proType);
    }

    @GetMapping("/findByProBranameLike/proBraname={proBraname}")//品牌模糊
    List<Product> findByProBranameLike(@PathVariable("proBraname") String proBraname){
        return productService.findByProBranameLike(proBraname);
    }

    @GetMapping("/findBySId/sId={sId}")
    List<Product> findBySId(@PathVariable("sId") Integer sId){
        return productService.findBySId(sId);
    }
    @GetMapping("/findByProType/proType={proType}")
    List<Product> findByProType(@PathVariable("proType") String proType){
        return productService.findByProType(proType);
    }
    @GetMapping("/findByProTypeDesc/proType={proType}")
    List<Product> findByProTypeDesc(@PathVariable("proType") String proType){
        return productService.findByProTypeDesc(proType);
    }
    @GetMapping("/findByAlleDesc")
    List<Product> findByAlleDesc(){
        return productService.findByAllDesc();
    }

    @GetMapping("/findByProBraname/proBraname={proBraname}")
    List<Product> findByProBraname(@PathVariable("proBraname") String proBraname){
        return productService.findByProBraname(proBraname);
    }

    @GetMapping("/findByProBranameDesc/proBraname={proBraname}")
    List<Product> findByProBranameDesc(@PathVariable("proBraname") String proBraname){
        return productService.findByProBranameLikeDesc(proBraname);
    }

    @GetMapping("/findByProPriceBetween/max={max}min={min}")
    List<Product> findByProPriceBetween(@PathVariable("max") double max,
                                   @PathVariable("min") double min){
        return productService.findByProPriceBetween(max,min);
    }
    @GetMapping("/findByProSkin/proSkin={proSkin}")
    List<Product> findByProSkin(@PathVariable("proSkin") String proSkin){
        return productService.findByProSkin(proSkin);
    }

    @PostMapping("/Picture")
    public void createPicture(@RequestParam("file") MultipartFile[] multipartFiles, HttpServletResponse response){
      //  String filePath="E:\\";
        boolean flag=false;
        MultipartFile multipartFile = multipartFiles[0];
        // String filename=multipartFile.getOriginalFilename();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String filename=simpleDateFormat.format(new Date())+".jpg";

        File dest=new File("D:\\code\\企业JAVA\\爱美丽——化妆品购物平台\\美丽菜菜\\爱美丽——化妆品购物平台\\前端\\前端\\final\\static\\pic\\"+filename);
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            try{
                multipartFile.transferTo(dest);
                flag=true;
                /*System.out.println("成功");*/
                response.sendRedirect("http://localhost:8081/#/addPic/"+"\\static\\pic\\"+filename);

            }
            catch (Exception e){
                e.printStackTrace();
            }


    }
/*
    @PostMapping("/UploadFile")
    @ResponseBody
    public String UploadFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                /*
                 * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到
                 * d:/files大家是否能实现呢？ 等等;
                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；
                 * 3、文件格式; 4、文件大小的限制;
                 */
/*
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(
                                file.getOriginalFilename())));
                System.out.println(file.getName());
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }

            return "上传成功";

        } else {
            return "上传失败，因为文件是空的.";
        }


        System.out.println("这还没错");

        // 如果目录不存在则创建
        File uploadDir = new File("./UploadFile");
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
      //  System.out.println(uploadPath);
        //判断文件是否为空
        System.out.println(file.isEmpty());
        if (!file.isEmpty()) {
            try {
                //文件的保存路径
                System.out.println("这里还没");
               // filePath = request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator + file.getOriginalFilename();
              //  System.out.println("这里还没错");
                //转存文件
                file.transferTo(new File("./UploadFile/"+file.getName()));
            } catch (Exception e) {
                System.out.println("这里错");
                e.printStackTrace();
            }
        }
        if(!file.isEmpty()){
            //File pic=new File("./UploadFiles/pic.png");

            try{
                System.out.println("这里还没错");
                file.transferTo(new File("./UploadFiles/pic.png"));
            }
            catch (Exception e){
                System.out.println("这里错");
                System.out.println(e.toString());
            }
        }

    }






  http:
    multipart:
      enabled: false
    */

}