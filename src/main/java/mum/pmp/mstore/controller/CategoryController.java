package mum.pmp.mstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mum.pmp.mstore.domain.Category;
import mum.pmp.mstore.service.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	
	  @GetMapping(value="/category")
	    public String categoriesList(Model model) {
	        model.addAttribute("categoriesList", categoryService.getCategories());
	        return "category/categoriesList";
	    }
	
//	  public String vendorCategories()
	
	  @GetMapping(value={"/categoriesEdit","/categoriesEdit/{id}"})
	    public String editCategory(Model model, @PathVariable(required = false, name = "id") Integer id) {
	        if (null != id) {
	            model.addAttribute("categories", categoryService.getCategory(id));
	        } else {
	            model.addAttribute("categories", new Category());
	        }
	        return "category/categoriesEdit";
	    }
	
	
	  @PostMapping(value="/categoriesEdit")
	    public String addCategory(Model model, Category category) {
		  categoryService.addCategory(category);	       
		  model.addAttribute("categoriesList", categoryService.getCategories());
	        return "category/categoriesList";
	    }
	  
	  
	  @DeleteMapping(value="/categoriesDelete/{id}")
	    public String deleteCategory(Model model, @PathVariable(required = true, name = "id") Integer id) {
		  	categoryService.deleteCategory(id);	        
			model.addAttribute("categoriesList", categoryService.getCategories());
	        return "category/categoriesList";
	    }

	  
}
