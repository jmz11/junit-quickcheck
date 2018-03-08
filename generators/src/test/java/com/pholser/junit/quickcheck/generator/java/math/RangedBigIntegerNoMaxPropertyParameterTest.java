/*
 The MIT License

 Copyright (c) 2010-2018 Paul R. Holser, Jr.

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.pholser.junit.quickcheck.generator.java.math;

import java.math.BigInteger;
import java.util.List;

import com.pholser.junit.quickcheck.generator.BasicGeneratorPropertyParameterTest;
import com.pholser.junit.quickcheck.generator.InRange;

import static java.math.BigInteger.*;
import static java.util.Arrays.*;
import static org.mockito.Mockito.*;

public class RangedBigIntegerNoMaxPropertyParameterTest
    extends BasicGeneratorPropertyParameterTest {

    @InRange(min = "-987654321987654321")
    public static final BigInteger TYPE_BEARER = null;

    private final BigInteger min = new BigInteger("-987654321987654321");

    @Override protected void primeSourceOfRandomness() {
        when(randomForParameterGenerator.nextBigInteger(min.add(TEN).subtract(min).bitLength()))
            .thenReturn(new BigInteger("6"));
        when(randomForParameterGenerator.nextBigInteger(min.add(TEN.pow(2)).subtract(min).bitLength()))
            .thenReturn(new BigInteger("35"));
        when(distro.sampleWithMean(1, randomForParameterGenerator)).thenReturn(0);
        when(distro.sampleWithMean(2, randomForParameterGenerator)).thenReturn(1);
    }

    @Override protected int trials() {
        return 2;
    }

    @Override protected List<?> randomValues() {
        return asList(new BigInteger("-987654321987654315"), new BigInteger("-987654321987654286"));
    }

    @Override public void verifyInteractionWithRandomness() {
        verify(randomForParameterGenerator).nextBigInteger(min.add(TEN).subtract(min).bitLength());
        verify(randomForParameterGenerator).nextBigInteger(min.add(TEN.pow(2)).subtract(min).bitLength());
    }
}
