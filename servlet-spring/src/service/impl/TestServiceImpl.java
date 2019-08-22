package service.impl;

import service.ITestService;

/**
 * @program: study-demo
 * @description:
 * @author: lxf
 * @create: 2019-08-22 14:51
 **/
public class TestServiceImpl implements ITestService {
    @Override
    public String getTestString() {
        return new String("成功了");
    }
}
