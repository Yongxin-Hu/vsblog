package run.vue.iblog.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import run.vue.iblog.common.Result;
import run.vue.iblog.entity.Blog;
import run.vue.iblog.service.BlogService;
import run.vue.iblog.util.ShiroUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yongxin
 * @since 2021-02-14
 */
@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    /**
     * 文章列表
     * @param currentPage
     * @return
     */
    @GetMapping("/blog")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){

        Page page = new Page(currentPage,5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.success("成功获取全部文章数据",pageData);
    }

    /**
     * 文章详情
     * @param id
     * @return
     */
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id")Long id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"本文章已被删除");
        return Result.success("成功读取文章数据",blog);
    }


    /**
     * 文章编辑
     * @param blog
     * @return
     */
    @RequiresAuthentication //认证后方可访问该接口
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog){

        Blog temp = null;
        Long currentUserId = ShiroUtils.getProfile().getId().longValue(); //获取当前用户id
        if(blog.getId()!=null){
            temp = blogService.getById(blog.getId());
            // 只能编辑自己的文章
            Assert.isTrue(temp.getUserId().longValue()==currentUserId,"无编辑权限");
        }else {
            //初始化新文章
            temp = new Blog();
            temp.setUserId(currentUserId);
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        //复制文章数据
        BeanUtil.copyProperties(blog,temp,"id","userId","created","status");

        blogService.saveOrUpdate(temp);
        return Result.success("成功发表文章");
    }

    /**
     * 文章删除
     * @param id
     * @return
     */
    @GetMapping("/blog/delete/{id}")
    public Result delete(@PathVariable(name = "id")String  id){
        boolean result = blogService.removeById(id);// 据库中为Long id
        Assert.isTrue(result,"本文章已被删除");
        return Result.success("成功删除本文章");
    }

    /**
     * 模糊查询
     * @param title
     * @return
     */
    @GetMapping("/blog/findByTitle")
    public Result findByTitle(@RequestParam(defaultValue = "") String title){
        List<Blog> blogs = blogService.list(new QueryWrapper<Blog>()
                .like("title", title)
                .orderByDesc("created"));
        Assert.notNull(blogs,"未查询到指定文章");
        return Result.success("成功查询到指定文章",blogs);
    }

    /**
     * 归档
     * @return
     */
    @GetMapping("/archive")
    public Result archive(){
        List<Blog> blogs = blogService.list(new QueryWrapper<Blog>()
                .orderByDesc("created"));
        Assert.notNull(blogs,"无文章");
        return Result.success("查询归档成功", blogs);
    }

    /**
     * 根据分类ID（cid）获取相关的博客
     * @return
     */
    @GetMapping("/blog/cid/{cid}")
    public Result findByCid(@PathVariable String cid){
        List<Blog> blogs = blogService.list(new QueryWrapper<Blog>().eq("cid", cid));
        return Result.success("查询分类博客成功", blogs);
    }
}
