/*
 The MIT License

 Copyright (c) 2010-2020 Paul R. Holser, Jr.

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

package com.pholser.junit.quickcheck.generator.java.lang;

import static com.pholser.junit.quickcheck.Generating.verifyLongs;
import static java.lang.Long.MIN_VALUE;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.pholser.junit.quickcheck.Generating;
import com.pholser.junit.quickcheck.generator.BasicGeneratorPropertyParameterTest;
import java.util.List;

public class PrimitiveLongPropertyParameterTest
    extends BasicGeneratorPropertyParameterTest {

    public static final long TYPE_BEARER = 3;

    @Override protected void primeSourceOfRandomness() {
        when(Generating.longs(randomForParameterGenerator))
            .thenReturn(MIN_VALUE);
    }

    @Override protected int trials() {
        return 1;
    }

    @Override protected List<?> randomValues() {
        return singletonList(MIN_VALUE);
    }

    @Override public void verifyInteractionWithRandomness() {
        verifyLongs(randomForParameterGenerator, times(1));
    }
}
