package com.kwin.controller;

import com.kwin.VO.ResultVO;
import com.kwin.converter.OrderForm2OrderDTOConverter;
import com.kwin.dto.OrderDTO;
import com.kwin.enums.ResultEnum;
import com.kwin.exception.SellException;
import com.kwin.form.OrderForm;
import com.kwin.service.BuyerService;
import com.kwin.service.OrderService;
import com.kwin.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[创建订单]购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = service.createOrder(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        ResultVO resultVO = ResultVOUtil.success(map);
        return resultVO;
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String buyerOpenid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(buyerOpenid)) {
            log.error("[查询订单列表] buyerOpenid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = service.findList(buyerOpenid, request);
        ResultVO resultVO = ResultVOUtil.success(orderDTOPage.getContent());
        return resultVO;
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String buyerOpenid,
                                     @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(buyerOpenid, orderId);
        ResultVO resultVO = ResultVOUtil.success(orderDTO);
        return resultVO;
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String buyerOpenid,
                           @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(buyerOpenid, orderId);
        return ResultVOUtil.success();
    }

}
