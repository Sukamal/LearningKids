package com.suku.learningkids.util;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class AppConstant {

    public static enum HomeMenu{
        ABC(1),
        NUMBER(2),
        COLOR(3),
        SHAPE(4),
        FLOWER(5),
        FRUIT(6),
        VEGETABLE(7),
        DOMESTICANIMAL(8),
        WILDANIMAL(9),
        BIRD(10),
        ;

        private int enumValue;

        HomeMenu(int enumValue){
            this.enumValue = enumValue;
        }

        public int getEnumValue() {
            return enumValue;
        }
    }

    public static enum ExtraTag{
        HOME_MENU_ACTION,
        FLAT_IMAGE_DISPLAY_CODE
    }


}
