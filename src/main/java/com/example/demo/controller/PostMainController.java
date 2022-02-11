package com.example.demo.controller;

import com.example.demo.model.CommentsAndRate;
import com.example.demo.model.Products;
import com.example.demo.model.Users;
import com.example.demo.repository.CommentsAndRateRepository;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostMainController {
    public static List<Products> productsList = new ArrayList<>();
    private static Long user_id;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    UserResource userResource;
    @Autowired
    CommentsAndRateRepository feedbackRepository;

    @Value("${image.upload.dir}")
    private String imageUploadDir;


    @PostMapping("/registration")
    public String home(Users users) {
        users.setAdmin(false);
        users.setBlock(false);
        userRepository.save(users);
        return "redirect:/addUser";
    }


    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
        Long id = null;
        Users user = userRepository.findUsersByEmail(email);
        if (user == null || user.getBlock()) {
            try {
                throw new IllegalAccessException("user not found");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (user.getPassword().equals(password)) {
            if (user.getAdmin()) {
                return "redirect:/admin";
            } else {
                user_id = user.getId();
                return "redirect:/log";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/addPro")
    public String addPro(@RequestParam("picture") MultipartFile[] multipartFile, Products products) throws IOException {
        File file = new File(imageUploadDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        for (MultipartFile multipartFile1 : multipartFile) {
            String fileName = System.currentTimeMillis() + "_" + products.getName() + multipartFile1.getOriginalFilename().toString().substring(multipartFile1.getOriginalFilename().toString().lastIndexOf("."));
            multipartFile1.transferTo(new File(imageUploadDir + fileName));
            products.setPictureUrl(fileName);
        }
        productsRepository.save(products);
        return "redirect:/admin";
    }


    @PostMapping("/updateProduct")
    public String updateProducts(@RequestParam("picture") MultipartFile[] multipartFile,
                                 Products products,
                                 @RequestParam("name") String name,
                                 @RequestParam("price") Long price,
                                 @RequestParam("description") String description,
                                 @RequestParam("rate") Integer rate,
                                 @RequestParam("id") Long id
    ) throws IOException {
        products = productsRepository.findProductsById(id);
        products.setName(name);
        products.setPrice(price);
        products.setDescription(description);
        products.setRate(rate);
        addPro(multipartFile, products);
        return "redirect:/admin";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("id") Long id) {
        productsRepository.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/allUsers")
    public String blockUser(@RequestParam("id") Long id) {
        Users updateUser = userResource.updateEmployee(userRepository.findUsersById(id));
        System.out.println(id);
        updateUser.setBlock(true);
        userRepository.save(updateUser);
        return "redirect:/admin";
    }

    @PostMapping("/unBlockUser")
    public String unBlockUser(@RequestParam("id") Long id) {
        Users updateUser = userResource.updateEmployee(userRepository.findUsersById(id));
        System.out.println(user_id);
        System.out.println(id);
        updateUser.setBlock(false);
        userRepository.save(updateUser);
        return "redirect:/admin";
    }

    @PostMapping("/addComment")
    public String addComment(
            @RequestParam("comment") String comment,
            @RequestParam("id") Long id,
            @RequestParam("rate") Integer rate,
            CommentsAndRate commentsAndRate) {
        Products products = productsRepository.findProductsById(id);
        Users users = userRepository.findUsersById(user_id);
        if (rate > 0 && rate < 5) {
            commentsAndRate.setComment(comment);
            commentsAndRate.setRate(rate);
            commentsAndRate.setProducts(products);
            commentsAndRate.setUsers(users);
            feedbackRepository.save(commentsAndRate);
        } else try {
            throw new IllegalAccessException("Size of Rate Illegal");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "redirect:/log";
    }

    @PostMapping("/search")
    public String search(ModelMap modelMap, @RequestParam("searchName") String searchName, Products products) {
        productsList.add(productsRepository.findProductsByName(searchName));
        return "redirect:/searchPro";
    }
}