package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StartSession extends JPanel {
    JButton startButton;
    TextFieldWithLengthLimit phoneNumber;
    TextFieldWithLengthLimit message;
    JScrollPane messageHolder;
    JLabel phoneTitle;
    JLabel messageTitle;
    ChromeDriver driver;

    public StartSession(int width, int height){
        this.setBounds(0,0, width, height);
        this.setBackground(Constants.BACKGROUND_COLOR);
        this.setLayout(null);
        this.startGui(width, height);
    }

    private void startGui(int width, int height) {
        this.phoneTitle = new JLabel("Phone Number:");
        this.phoneTitle.setFont(Constants.FONT);
        this.phoneTitle.setBounds((width/8), (height/8), Constants.PHONE_TITLE_WIDTH, Constants.PHONE_TITLE_HEIGHT);
        this.add(this.phoneTitle);
        this.phoneNumber = new TextFieldWithLengthLimit(this.phoneTitle.getX()+ this.phoneTitle.getWidth(), this.phoneTitle.getY(), Constants.PHONE_WIDTH, Constants.PHONE_TITLE_HEIGHT, Constants.PHONE_MAX_CHAR);
        this.add(this.phoneNumber);
        this.messageTitle = new JLabel("Message:");
        this.messageTitle.setFont(Constants.FONT);
        this.messageTitle.setBounds(this.phoneTitle.getX(), this.phoneTitle.getY() + this.phoneNumber.getHeight(), Constants.PHONE_TITLE_WIDTH, Constants.PHONE_TITLE_HEIGHT);
        this.add(this.messageTitle);
        this.message = new TextFieldWithLengthLimit(this.messageTitle.getX()+ this.messageTitle.getWidth(), this.messageTitle.getY(), Constants.MESSAGE_BOX_WIDTH, Constants.MESSAGE_BOX_HEIGHT, Constants.MESSAGE_MAX_CHAR);
        this.messageHolder = new JScrollPane();
        this.messageHolder.setBounds(this.message.getBounds());
        this.messageHolder.setViewportView(this.message);
        this.add(this.messageHolder);
        this.startButton = new JButton();
        this.startButton.setText("START");
        this.startButton.addActionListener((e)-> {
            this.buttonActionManage();
        });
        this.startButton.setBounds(this.phoneTitle.getX(), this.phoneTitle.getY() + this.phoneTitle.getHeight() * 3, width/5, height/5);
        this.add(this.startButton);
    }

    private void buttonActionManage() {
        String phone = this.phoneNumber.getText();
        String message = this.message.getText();
        if (phone.length() < 1){
            this.showAnnouncement("Please Enter a Phone Number!");
        }else if (phone.length() < Constants.PHONE_MAX_CHAR){
            this.showAnnouncement("Phone Number too Short!");
        }else if (!phone.matches(Constants.ONLY_DIGITS)){
            this.showAnnouncement("Phone Number Invalid, Please Enter only Digits!");
        }else if (!phone.startsWith(Constants.PHONE_PREFIX)){
            this.showAnnouncement("Phone Number Invalid, Please Enter a Valid Phone Number that starts with '05'");
        }else if (message.length() < 1){
            this.showAnnouncement("Please Enter Content for the Message!");
        }else {
            this.login(phone, message);
        }
    }

    private void showAnnouncement(String text) {
        JOptionPane.showMessageDialog(this, text);
    }

    private void login(String phoneNumber, String message) {
        this.driver = new ChromeDriver();
        this.driver.get(Constants.URL_PREFIX + phoneNumber.substring(1));
        while (this.driver.findElements(By.id(Constants.CHATS_RECOGNIZED)).isEmpty()){
            Utils.sleep(500);
        }
        List<WebElement> textField;
        do {
            Utils.sleep(20);
            textField = this.driver.findElements(By.cssSelector(Constants.TEXT_FIELD_CSS_SELECTOR));
        }while (textField.isEmpty());
        textField.get(0).sendKeys(message);
        this.driver.findElement(By.xpath(Constants.SEND_BUTTON_XPATH)).click();
        List<WebElement> messagesInChat;
        List<WebElement> lastElementDetails;
        boolean sent = false;
        do {
            Utils.sleep(1000);
            messagesInChat = this.driver.findElements(By.cssSelector("div.message-out"));
            lastElementDetails = messagesInChat.get(messagesInChat.size() - 1).findElements(By.cssSelector("[aria-label*=' '"));
            if (!lastElementDetails.isEmpty()){
                if (lastElementDetails.stream().anyMatch(this::sentMessage)){
                    sent = true;
                }
            }
        }while (!sent);
        this.showAnnouncement( "Message Sent!");
        this.driver.close();
    }
    private boolean sentMessage(WebElement m){
        String attr = m.getAttribute("aria-label");
        return attr.equals(Constants.E_SENT) || attr.equals(Constants.E_DELIVERED) || attr.equals(Constants.E_READ)
                || attr.equals(Constants.H_SENT) || attr.equals(Constants.H_DELIVERED) || attr.equals(Constants.H_READ);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.startButton.paintComponents(g);
    }
}
