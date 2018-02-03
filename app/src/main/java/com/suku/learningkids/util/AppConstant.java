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
        INSECTS(11),
        SEACREATURES(12),
        FOOD(13),
        VEHICLES(14),
        KITCHEN(15),
        BATHROOM(16),
        BEDROOM(17),
        STATIONARY(18),
        MUSICALINSTRUMENT(19),
        SPORTSEQUIPEMENT(20),
        SEASONS(21),
        MONTHS(22),
        TIMEANDCALENDER(23),
        COMPUTERPARTS(24),



        ACTIONS(25)

        ;

        private int enumValue;

        HomeMenu(int enumValue){
            this.enumValue = enumValue;
        }

        public int getEnumValue() {
            return enumValue;
        }
    }

    public static enum AppType{
        FREE(1),
        PAID(2)
        ;

        private int enumValue;

        AppType(int enumValue){
            this.enumValue = enumValue;
        }

        public int getEnumValue() {
            return enumValue;
        }
    }

    public enum Preferences {
        APPLICATION_TYPE
    }

    public static enum ExtraTag{
        HOME_MENU_ACTION,
        FLAT_IMAGE_DISPLAY_CODE
    }


}
