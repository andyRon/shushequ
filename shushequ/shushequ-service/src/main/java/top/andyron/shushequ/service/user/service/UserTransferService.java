package top.andyron.shushequ.service.user.service;

/**
 * @author andyron
 * @date 2026/4/17
 */
public interface UserTransferService {

    boolean transferUser(String uname, String pwd);

    boolean transferUser(String starNumber);
}
