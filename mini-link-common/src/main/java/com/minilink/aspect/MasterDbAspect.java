//package com.minilink.aspect;
//
//import com.minilink.annotaion.Master;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shardingsphere.infra.hint.HintManager;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import java.util.Objects;
//
///**
// * @Author 徐志斌
// * @Date: 2024/12/9 13:59
// * @Version 1.0
// * @Description: 选中主库-切面类
// */
//@Slf4j
//@Aspect
//@Component
//public class MasterDbAspect {
//
//    @Around("@annotation(master)")
//    public void master(ProceedingJoinPoint joinPoint, Master master) {
//        try (HintManager hintManager = HintManager.getInstance()) {
//            if (Objects.nonNull(master)) {
//                HintManager.clear();
//                hintManager.setWriteRouteOnly();
//            }
//        } catch (Exception ex) {
//            log.error("exception error", ex);
//        } catch (Throwable ex2) {
//            log.error("Throwable", ex2);
//        }
//    }
//}
