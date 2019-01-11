package com.kwin.controller;


import com.kwin.dto.OrderDTO;
import com.kwin.enums.OrderStatusEnum;
import com.kwin.enums.ResultEnum;
import com.kwin.exception.SellException;
import com.kwin.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> model) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        model.put("orderDTOPage", orderDTOPage);
        model.put("currentPage", page);
        model.put("size", size);
        return new ModelAndView("order/list", model);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String, Object> model) {
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
            model.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
            model.put("url", "/sell/seller/order/list");
        } catch (SellException e) {
            log.error("[取消订单]发生异常");
            model.put("msg", e.getMessage());
            model.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", model);
        }
        return new ModelAndView("common/success", model);
    }
}
