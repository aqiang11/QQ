package com.Dao;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;

public class FriendNode extends DefaultMutableTreeNode {

    private  String FriendAccount;
    protected ImageIcon icon;
    protected String NickName;
    protected String isOnline;

    public FriendNode() {

    }

    public FriendNode(String NickName, String isOnline) {
        super();
        this.NickName = NickName;
        this.isOnline = isOnline;
    }

    /**
     * 包含好友头像、昵称、是否在线的节点构造函数
     *
     * @param icon
     * @param NickName
     * @param isOnline
     */
    public FriendNode(ImageIcon icon, String NickName, String isOnline,String FriendAccount) {
        super();
        this.icon = icon;
        this.NickName = NickName;
        this.isOnline = isOnline;
        this.FriendAccount = FriendAccount;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String  getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public String  getFriendAccount() {
        return FriendAccount;
    }

    public void setFriendAccount(String userAccount) {
        this.FriendAccount = userAccount;
    }
}
