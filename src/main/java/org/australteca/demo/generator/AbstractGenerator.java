package org.australteca.demo.generator;

import java.util.Random;

/**
 * Created by tomi on 12/07/17.
 */
abstract class AbstractGenerator implements Generator {

    protected Random random = new Random();

    protected int getIndex(int low, int high, double percentage){
        double index = (high - low)*percentage + low;
        return (int) Math.floor(index) ;
    }
}
