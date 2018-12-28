package com.kwin.util;

import com.kwin.VO.ResultVO;

public class ResultVOUtil {

    public static ResultVO<Object> success(Object object){
        ResultVO resultVO= new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO<Object> success(){
        return success(null);
    }

    public static ResultVO<Object> error(Integer code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }

}
