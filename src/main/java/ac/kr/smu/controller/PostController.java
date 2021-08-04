package ac.kr.smu.controller;

import ac.kr.smu.service.PostService;
import ac.kr.smu.vo.PostVO;
import ac.kr.smu.vo.UserVO;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping({"/{postId}",""})
    public String getPost(@PathVariable(value = "postId", required = false) Integer postId,
                          @RequestParam(value = "isModify", required = false) boolean isModify, Model model){
        if(postId != null)
            model.addAttribute("post", postService.findById(postId));

        if (isModify)
            model.addAttribute("isModify", true);
        else
            model.addAttribute("isModify", false);

            return "post";
    }


    @PutMapping
    public @ResponseBody boolean putPost(@RequestBody PostVO postVO,Model model){
        model.addAttribute("post", postService.update(postVO));
        model.addAttribute("isModify", false);
        return true;
    }

    @DeleteMapping("/{postId}")
    public @ResponseBody boolean deletePost(@PathVariable int postId){
        postService.delete(postId);
        return true;
    }

    @PostMapping
    public @ResponseBody int postPost(@RequestBody PostVO postVO, UserVO user){
        postVO.setUser(user);
        postService.save(postVO);
        return postVO.getId();
    }
}