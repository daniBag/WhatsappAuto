package org.example;

import java.awt.*;

public class Constants {
    public static final String E_READ = " Read ";
    public static final String H_READ = " נקראה ";
    public static final String E_DELIVERED = " Delivered ";
    public static final String H_DELIVERED = " נמסרה ";
    public static final String E_SENT = " Sent ";
    public static final String H_SENT = " נשלחה ";
    public static final String E_PENDING = " Pending ";
    public static final String H_PENDING = " בהמתנה ";
    public static final Color BACKGROUND_COLOR = new Color(40, 82, 85, 168);
    public static final Font FONT = new Font("ariel", Font.PLAIN, 14);
    public static final int PHONE_TITLE_WIDTH = 150;
    public static final int PHONE_TITLE_HEIGHT = 20;
    public static final int PHONE_WIDTH = 100;
    public static final int PHONE_MAX_CHAR = 10;
    public static final int MESSAGE_BOX_WIDTH = 200;
    public static final int MESSAGE_BOX_HEIGHT = 100;
    public static final int MESSAGE_MAX_CHAR = 300;
    public static final String PHONE_PREFIX = "05";
    public static final String ONLY_DIGITS = "^\\d{10}$";
    public static final String URL_PREFIX = "https://web.whatsapp.com/send?phone=972";
    public static final String CHATS_RECOGNIZED = "wa-popovers-bucket";
    public static final String TEXT_FIELD_CSS_SELECTOR = "._3Uu1_ > div:nth-child(1) > div:nth-child(1) > p:nth-child(1)";
    public static final String SEND_BUTTON_XPATH = "/html/body/div[1]/div/div/div[5]/div/footer/div[1]/div/span[2]/div/div[2]/div[2]/button/span";
}
