package com.example.demo.controller;

import com.example.demo.model.CommentsAndRate;
import com.example.demo.model.Products;
import com.example.demo.model.Users;
import com.example.demo.repository.CommentsAndRateRepository;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.repository.UserRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GetMainController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    CommentsAndRateRepository commentsAndRateRepository;

    //    @Value("C:\\Users\\DELL\\IdeaProjects\\ShoppingApplication\\Images\\")
    @Value("${image.upload.dir}")
    private String imageUploadDir;


    @GetMapping("/")
    public String indexs(ModelMap modelMap) {
        return "login";
    }

    @GetMapping("/addUser")
    public String login(ModelMap modelMap) {
        modelMap.addAttribute("registration", new Users());
        return "registration";
    }

    @GetMapping("/log")
    public String index(ModelMap modelMap) {
        List<Products> products = productsRepository.findAll();
        modelMap.addAttribute("allProducts", products);
        return "index";
    }

    @GetMapping("/admin")
    public String indexForAdmin(ModelMap modelMap) {
        List<Products> products = productsRepository.findAll();
        modelMap.addAttribute("allProduct", products);
        return "indexadmin";
    }

    @GetMapping("/addProduct")
    public String addProducts(ModelMap modelMap) {
        modelMap.addAttribute("addProducts", new Products());
        return "products";
    }

    @GetMapping("/update")
    public String updateProducts(ModelMap modelMap, @RequestParam("id") Long id) {
        System.out.println("Tvyal producti id" + id);
        modelMap.addAttribute("updateProduct", productsRepository.findProductsById(id));
        return "update";
    }

    @GetMapping("/imageProduct")
    public void getImageProduct(HttpServletResponse response, @RequestParam("fileName") String fileName) throws IOException {
//        Products productImage = productsRepository.findProductsByPictureUrl(fileName);
//        InputStream in = new FileInputStream(imageUploadDir + productImage.getPictureUrl());
        InputStream in = new FileInputStream(imageUploadDir + fileName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    @GetMapping("/blockUser")
    public String blockUsers(ModelMap modelMap) {
        List<Users> users = userRepository.findAll();
        List<Users> activeUsers = new ArrayList<>();
        for (Users user : users) {
            if (user.getBlock() && !user.getAdmin())
                activeUsers.add(user);
        }
        modelMap.addAttribute("allUser", activeUsers);
        return "blockuser";
    }

    @GetMapping("/userInAdminPage")
    public String userInPage(ModelMap modelMap) {
        List<Users> users = userRepository.findAll();
        List<Users> activeUsers = new ArrayList<>();
        for (Users user : users) {
            if (!user.getBlock() && !user.getAdmin())
                activeUsers.add(user);
        }
        modelMap.addAttribute("allUser", activeUsers);
        return "userinadmingpage";
    }

    @GetMapping("/viewComment")
    public String viewComment(ModelMap modelMap) {
        List<CommentsAndRate> commentsAndRates = commentsAndRateRepository.findAll();
        modelMap.addAttribute("comment", commentsAndRates);
        return "viewcomment";
    }

    @GetMapping("/searchPro")
    public String searchProduct(ModelMap modelMap) {
        modelMap.addAttribute("searchList", PostMainController.productsList);
        return "serachproduct";
    }

//    @GetMapping("/search")
//    public String searchPro(ModelMap modelMap, @RequestParam("searchName") String searchName, Products products) {
//        if (searchName.equals(products.getName())) {
//            modelMap.addAttribute("searchProduct", productsRepository.findProductsByName(searchName));
//        } else {
//            return "index";
//        }
//        return "serachproduct";
//    }
}


