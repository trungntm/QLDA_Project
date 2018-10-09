package vn.hcmute.projectmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.hcmute.projectmanagement.api.v1.data.DataReturnList;
import vn.hcmute.projectmanagement.api.v1.data.DataReturnOne;
import vn.hcmute.projectmanagement.api.v1.dto.ProductInCartDto;
import vn.hcmute.projectmanagement.api.v1.dto.UserDto;
import vn.hcmute.projectmanagement.api.v1.mapper.ProductInCartMapper;
import vn.hcmute.projectmanagement.api.v1.mapper.UserMapper;
import vn.hcmute.projectmanagement.entity.*;
import vn.hcmute.projectmanagement.service.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductInCartMapper productInCartMapper;

    @Autowired
    private Cart_ProductService cart_productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PersonService personService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceProductService invoiceProductService;

    @GetMapping("/users")
    public String user(){
        return "permit user";
    }
    // register user
    @PostMapping("/register")
    public UserDto registerUser(@RequestBody User user){
        return userMapper.userToUserDto(userService.registerUser(user));

    }

    @GetMapping("/productincart/{userName}")
    public DataReturnList<ProductInCartDto> getProductInCart(@PathVariable String userName){
        System.out.println(userName);
        Cart cart = userService.retrieveUserByUsername(userName).getPerson().getCart();

        DataReturnList<ProductInCartDto> productInCartDtoDataReturnList=new DataReturnList<>();
        productInCartDtoDataReturnList.setData(cart_productService.retrieveAllCartProduct(cart.getId())
                .stream()
                .map(productInCartMapper::cartProductToProductInCartDto)
                .collect(Collectors.toList()));
        productInCartDtoDataReturnList.setMessage("success");
        return productInCartDtoDataReturnList;
    }

    @PostMapping("/addproductincart/{userName}/{productId}/{quantity}")
    public ProductInCartDto addProductInCart(@PathVariable String userName, @PathVariable Long productId, @PathVariable Long quantity){
        Cart cart = userService.retrieveUserByUsername(userName).getPerson().getCart();
        Product product = productService.getProductById(productId);

        Cart_Product_Id cart_product_id = new Cart_Product_Id();
        cart_product_id.setCart(cart);
        cart_product_id.setProduct(product);

        Cart_Product cart_product = new Cart_Product();
        cart_product.setId(cart_product_id);
        cart_product.setQuantity(quantity);

        return productInCartMapper.cartProductToProductInCartDto(cart_productService.addProductInCart(cart_product));
    }

    @DeleteMapping("/deleteproductincart/{userName}/{productId}")
    public ProductInCartDto deleteProductInCart(@PathVariable String userName, @PathVariable Long productId){
        Cart cart = userService.retrieveUserByUsername(userName).getPerson().getCart();
        Product product = productService.getProductById(productId);

        Cart_Product_Id cart_product_id = new Cart_Product_Id(cart, product);

        Cart_Product cart_product = new Cart_Product();
        cart_product.setId(cart_product_id);

        cart_productService.deleteProductInCart(cart_product);

        return productInCartMapper.cartProductToProductInCartDto(cart_product);
    }

    @DeleteMapping("/deleteallproductincart/{userName}")
    public boolean deleteAllProductInCart(@PathVariable String userName){
        Cart cart = userService.retrieveUserByUsername(userName).getPerson().getCart();
        return cart_productService.deleteAllProductInCart(cart.getId());
    }

    @PostMapping("/thanhtoan/{userName}")
    public boolean thanhToan(@PathVariable String userName){
        Person person = userService.retrieveUserByUsername(userName).getPerson();
        Date date = new Date();
        Invoice invoice = new Invoice();
        invoice.setTotal(Long.valueOf(0));
        invoice.setStatus(Long.valueOf(0));
        invoice.setPerson(person);
        invoice.setDateCreated(date);
        invoice.setDateUpdated(date);
        invoice.setUserCreated(person.getFullName());
        invoice.setUserUpdated(person.getFullName());

        Invoice invoiceAdd = invoiceService.addInvoice(invoice);

        System.out.println(invoiceAdd.getId());

        Cart cart = personService.findPersonById(person.getId()).getCart();
        List<Cart_Product> cart_products = cart_productService.retrieveAllCartProduct(cart.getId());

        List<Invoice_Product> invoice_products = new ArrayList<>();

        for (Cart_Product cart_product: cart_products) {
            Invoice_Product_Id invoice_product_id = new Invoice_Product_Id();
            invoice_product_id.setInvoice(invoiceAdd);
            invoice_product_id.setProduct(cart_product.getId().getProduct());

            Invoice_Product invoice_product = new Invoice_Product();
            invoice_product.setId(invoice_product_id);
            invoice_product.setPrice(cart_product.getId().getProduct().getPrice());
            invoice_product.setQuantity(cart_product.getQuantity());

            invoice_products.add(invoiceProductService.addInvoiceProduct(invoice_product));
        }

        return cart_productService.deleteAllProductInCart(cart.getId());
    }
}
