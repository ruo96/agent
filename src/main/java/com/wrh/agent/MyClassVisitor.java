package com.wrh.agent;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * 自定义ClassVisitor，修改foo方法字节码
 * @author xujian
 * 2021-03-12 11:14
 **/
public class MyClassVisitor extends ClassVisitor {
    public MyClassVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
                                     String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if ("foo".equals(name)) {
            System.out.println("----准备修改foo方法----");
            return new MyMethodVisitor(api,mv,access,name,descriptor);
        }
        return mv;
    }
}
