package com.usman.forum.Annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoleTypeValidation  implements ConstraintValidator<RoleTypeAnnotation, String> {


    @Override
    public boolean isValid(String number, ConstraintValidatorContext constraintValidatorContext) {

        log.info(number+"sfdkafksdkfsdaaaaaaaaaaaaaaaaaaaaaaa");
       try{
           Integer k=Integer.parseInt(number);
           log.info("\nsd-f-saf-sd-fd-fd-"+k+"\n");
           return true;
       }catch (NumberFormatException e){

       }
       return false;
    }
}
