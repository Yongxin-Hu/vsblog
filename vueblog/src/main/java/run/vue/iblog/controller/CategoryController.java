package run.vue.iblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import run.vue.iblog.common.Result;
import run.vue.iblog.entity.Category;
import run.vue.iblog.service.CategoryService;

import java.util.List;

/**
 * @author yongxin
 * @Date 2021/5/22
 */
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 根据cid获取分类
     * @param cid 分类ID
     * @return
     */
    @GetMapping("/category/{cid}")
    public Result category(@PathVariable Long cid){
        Category category = categoryService.getById(cid);
        Assert.notNull(category, "分类不存在");
        return Result.success("成功获取分类", category);
    }

    /**
     * 获取分类列表
     * @return
     */
    @GetMapping("/categories")
    public Result categories(){
        List<Category> categories = categoryService.list(
                new QueryWrapper<Category>().orderByDesc("cid"));
        return Result.success("成功获取所有分类", categories);
    }
}
