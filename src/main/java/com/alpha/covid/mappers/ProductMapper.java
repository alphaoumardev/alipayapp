package com.alpha.covid.mappers;

import com.alpha.covid.beans.vo.ProductStockVo;
import com.alpha.covid.beans.vo.ProductVo;
import com.alpha.covid.models.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product>
{

    List<ProductStockVo> findProductstock(Integer page, Integer size, ProductVo productVo);

    long findProductStockCount(ProductVo productVo);

    List<ProductStockVo> getAllProducts(ProductVo productVo);
}
