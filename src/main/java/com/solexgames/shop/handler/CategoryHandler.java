package com.solexgames.shop.handler;

import com.solexgames.shop.category.ShopCategory;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

@Getter
public class CategoryHandler {

    private final List<ShopCategory> categories = new ArrayList<>();

}
