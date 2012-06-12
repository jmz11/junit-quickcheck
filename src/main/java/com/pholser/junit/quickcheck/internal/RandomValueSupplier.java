/*
 The MIT License

 Copyright (c) 2010-2011 Paul R. Holser, Jr.

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

package com.pholser.junit.quickcheck.internal;

import java.util.List;

import com.pholser.junit.quickcheck.ForAll;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.internal.generator.BasicGeneratorSource;
import com.pholser.junit.quickcheck.internal.generator.GeneratorRepository;
import com.pholser.junit.quickcheck.internal.generator.ServiceLoaderGeneratorSource;
import com.pholser.junit.quickcheck.internal.generator.RandomTheoryParameterGenerator;
import com.pholser.junit.quickcheck.internal.generator.TheoryParameterGenerator;
import com.pholser.junit.quickcheck.internal.random.JDKSourceOfRandomness;
import org.junit.contrib.theories.ParameterSignature;
import org.junit.contrib.theories.ParameterSupplier;
import org.junit.contrib.theories.PotentialAssignment;

public class RandomValueSupplier extends ParameterSupplier {
    private final TheoryParameterGenerator generator;

    /**
     * Called by JUnit reflectively.
     */
    public RandomValueSupplier() {
        this(new RandomTheoryParameterGenerator(new JDKSourceOfRandomness(),
            new GeneratorRepository().add(new BasicGeneratorSource()).add(new ServiceLoaderGeneratorSource())));
    }

    protected RandomValueSupplier(TheoryParameterGenerator generator) {
        this.generator = generator;
    }

    @Override
    public List<PotentialAssignment> getValueSources(ParameterSignature signature) {
        ParameterContext context = new ParameterContext(signature.getType());
        context.addQuantifier(signature.getAnnotation(ForAll.class));
        From explicitGenerators = signature.getAnnotation(From.class);
        if (explicitGenerators != null)
            context.addGenerators(explicitGenerators);

        return generator.generate(context);
    }
}