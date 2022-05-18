package util;

public class OutColor {

    public static String paint(String string,OutColors outColors){
       return outColors.getCode()+string+OutColors.ANSI_RESET.getCode();
    }
}
