package com.wrh.agent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

import static org.objectweb.asm.Opcodes.ASM7;

/**
 * 自定义类文件转换器，通过ASM修改MyBizMain类字节码
 * @author xujian
 * 2021-03-12 11:42
 **/
public class MyClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        System.out.println("transform className = " + className);
        if (!"com/wrh/attach/MyBizMain".equals(className)) {

            return classfileBuffer;
        }
        //以下为ASM常规操作，详情可以查看ASM使用相关文档
        ClassReader cr = new ClassReader(classfileBuffer);
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_FRAMES);
        ClassVisitor cv = new MyClassVisitor(ASM7,cw);
        cr.accept(cv,ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);
        return cw.toByteArray();
    }
}
