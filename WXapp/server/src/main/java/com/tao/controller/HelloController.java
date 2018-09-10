package com.tao.controller;

import com.tao.annotation.Log;
import com.tao.business.MenuService;
import com.tao.business.OrderService;
import com.tao.common.Result;
import com.tao.common.ResultEnum;
import com.tao.entity.dto.MenuItemDTO;
import com.tao.entity.po.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Resource
    private MenuService menuBizService;

    @Resource
    private OrderService orderBizService;

    /**
     * 保存菜单文本
     *
     * @param menuTxt
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @Log(description = "保存菜单")
    @RequestMapping(value = "saveMenu", method = RequestMethod.POST)
    public Result<Object> writeMenuToFile(@RequestParam("menuTxt") String menuTxt, HttpServletRequest request) throws UnsupportedEncodingException {
        menuTxt = new String(menuTxt.getBytes("iso8859-1"), "utf-8");
        try {
            Result<Object> result = new Result<>();
            result.setCode(ResultEnum.SUCCESS.getCode());
            result.setMsg(ResultEnum.SUCCESS.getMsg());
            result.setData(menuBizService.publishMenu(menuTxt));
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        Result<Object> result = new Result<>();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(ResultEnum.ERROR.getMsg());
        return result;
    }


    /**
     * 获取菜单文本
     *
     * @return
     */
    @Log(description = "获取菜单文本")
    @RequestMapping(value = "menuTxt", method = RequestMethod.GET)
    public Result<List<MenuItemDTO>> getMenuFromFile() {
        try {
            Result<List<MenuItemDTO>> result = new Result<>();
            result.setCode(ResultEnum.SUCCESS.getCode());
            result.setMsg(ResultEnum.SUCCESS.getMsg());
            result.setData(menuBizService.latestMenu());
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        Result<List<MenuItemDTO>> result = new Result<>();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(ResultEnum.ERROR.getMsg());
        return result;
    }

    /**
     * 保存用户点餐选项
     */
    @Log(description = "保存用户点餐选项")
    @RequestMapping(value = "order/{userId}/{orderId}", method = RequestMethod.POST)
    public Result<MenuItemDTO> saveOrder(@PathVariable String userId, @PathVariable String orderId) {
        try {
            Result<MenuItemDTO> result = new Result<>();
            result.setCode(ResultEnum.SUCCESS.getCode());
            result.setMsg(ResultEnum.SUCCESS.getMsg());
            result.setData(orderBizService.addOrder(userId, orderId));
            return result;
        } catch (IllegalArgumentException e) {
            Result<MenuItemDTO> result = new Result<>();
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg(e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        Result<MenuItemDTO> result = new Result<>();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(ResultEnum.ERROR.getMsg());
        return result;
    }

    /**
     * 读取点餐选项
     */
    @Log(description = "读取点餐选项")
    @RequestMapping(value = "getOrders", method = RequestMethod.GET)
    public Result<List<Order>> getOrders() {
        return null;
    }
}