package com.xm.mall.api;

import com.xm.mall.utils.Result;
import com.xm.mall.utils.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * 商品api
 *
 * @author huyan
 * @date 2019-04-11 09:29
 */
@RestController

@CrossOrigin //解决跨域问题

@Api(value = "商品信息",produces = MediaType.APPLICATION_JSON_VALUE,tags = "商品信息相关API")
public class GoodsApiController {


//    @ApiOperation(value = "根据参数查询商品并分页展示",notes = "根据参数查询商品并分页展示",produces = "application/json")



//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageSize",value = "页数",defaultValue = "1"),
//            @ApiImplicitParam(name = "pageNum",value = "每页展示的记录数",defaultValue = "30"),
//            @ApiImplicitParam(name = "goodsReqVO",value = "查询条件的参数")
//    })



//    @GetMapping("/goods")
//    public Object find(
//                       @ApiParam(value = "页码",hidden = true) @RequestParam(defaultValue = "1") Integer pageSize,
//                       @ApiParam(value = "每页显示的数据",hidden = true) @RequestParam(defaultValue = "30") Integer pageNum){
//
//        //TODO 根据查询条件,

//        List<Goods> goods = goodsService.findByParam(goodsReqVO, pageSize, pageNum);

//        return ResultData.success(goods);
//        return Result.success().put("goods",goods);
//    }
//
//
//    @PostMapping("/goods")
//    public Result save(@ApiParam("查询参数") GoodsReqVO goodsReqVO){
//        //TODO 报错数据,
//        return ResultData.success();
//    }
//
//    @PutMapping("/goods")
//    public Result update(@ApiParam("查询参数") GoodsReqVO goodsReqVO){
//        //TODO 报错数据,
//        return ResultData.success();
//    }
//
//    @DeleteMapping("/goods")
//    public Result delete(@ApiParam("查询参数") GoodsReqVO goodsReqVO){
//        //TODO 报错数据,
//        return ResultData.success();
//    }


}
