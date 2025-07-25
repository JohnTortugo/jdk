/*
 * Copyright (c) 2016, 2025, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package jdk.jfr;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Event field annotation, specifies that a value represents an amount of data (for example, bytes).
 * <p>
 * The following example shows how the {@code DataAmount} annotation can be used to
 * set the units {@code BITS} and {@code BYTES} to event fields.
 *
 * {@snippet class="Snippets" region="DataAmountOverview"}
 *
 * @since 9
 */
@MetadataDefinition
@ContentType
@Label("Data Amount")
@Description("Amount of data")
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
public @interface DataAmount {
    /**
     * Unit for bits
     */
    public static final String BITS = "BITS";
    /**
     * Unit for bytes
     */
    public static final String BYTES = "BYTES";

    /**
     * Returns the unit for the data amount, by default bytes.
     *
     * @return the data amount unit, default {@code BYTES}
     */
    String value() default BYTES;
}
