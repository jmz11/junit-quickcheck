/*
 The MIT License

 Copyright (c) 2010-2012 Paul R. Holser, Jr.

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

package com.pholser.junit.quickcheck.internal.generator;

import com.pholser.junit.quickcheck.generator.ShortGenerator;
import com.pholser.junit.quickcheck.internal.random.SourceOfRandomness;
import com.pholser.junit.quickcheck.test.generator.AnotherShortGenerator;
import com.pholser.junit.quickcheck.test.generator.Foo;
import com.pholser.junit.quickcheck.test.generator.FooGenerator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.pholser.junit.quickcheck.internal.generator.Generators.*;

public class RegisteringGeneratorsWithServiceLoaderTest {
    private GeneratorRepository repo;
    @Mock private SourceOfRandomness random;

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);

        repo = new GeneratorRepository(random);
        repo.add(new BasicGeneratorSource()).add(new ServiceLoaderGeneratorSource());
    }

    @Test
    public void bringsInTypesOtherThanBasicTypes() {
        assertGenerators(repo.generatorFor(Foo.class), FooGenerator.class);
    }

    @Test
    public void bringsInTypesToSupplementBasicTypes() {
        assertGenerators(repo.generatorFor(short.class), ShortGenerator.class, AnotherShortGenerator.class);
    }
}
