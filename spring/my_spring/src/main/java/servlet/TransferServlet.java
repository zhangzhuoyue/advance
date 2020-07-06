package servlet;

import factory.FactoryBean;
import factory.ProxyFactory;
import pojo.Result;
import service.TransferService;
import service.impl.TransferServiceImpl;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zzy
 * @date 2020/7/2 21:59
 */
@WebServlet(name = "transferServlet", urlPatterns = "/transferServlet")
public class TransferServlet extends HttpServlet {

    //private FactoryBean BeanFactory;
    //从工厂类中获取service实现类
    //private TransferService transferService = (TransferService)FactoryBean.getBean("transferService");
    //使用代理工厂
    //FactoryBean.getBean("");
    //private ProxyFactory proxyFactory = (ProxyFactory) FactoryBean.getBean("proxyFactory");
    //private ProxyFactory proxyFactory = (ProxyFactory) FactoryBean.getBean("proxyFactory");
    private ProxyFactory proxyFactory = (ProxyFactory) FactoryBean.getBean("proxyFactory");
    private TransferService transferService = (TransferService) proxyFactory.getJDKProxyObject(FactoryBean.getBean("transferService"));




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("utf-8");

        String fromCardNo = req.getParameter("fromCardNo");
        String toCardNo = req.getParameter("toCardNo");
        String moneyStr = req.getParameter("money");
        int money = Integer.parseInt(moneyStr);

        Result result = new Result();

        try {
            //调用service层
            transferService.transfer(fromCardNo, toCardNo, money);
            result.setStatus("200");

        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("201");
            result.setMessage(e.getMessage());
        }

        //响应
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(JsonUtils.object2Json(result));
    }
}
