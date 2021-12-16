package com.wrh.agent;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;


/**
 * 自定义MethodVisitor，修改字节码
 * @author xujian
 * 2021-03-12 11:02
 **/
public class MyMethodVisitor extends AdviceAdapter {


    protected MyMethodVisitor(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(api, methodVisitor, access, name, descriptor);
    }

    @Override
    protected void onMethodEnter() {
        mv.visitLdcInsn("------我是MyBizMain的Agent-----");//从常量池加载字符串
        mv.visitInsn(ARETURN);//返回
    }
}
