/*
 * Copyright (c) 2017 Tatsuya Maki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.t28.json2java.idea.validator;

import com.intellij.openapi.ui.InputValidatorEx;
import com.intellij.psi.PsiNameHelper;
import io.t28.json2java.idea.Json2JavaBundle;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

public class NameValidator implements InputValidatorEx {
    private final Json2JavaBundle bundle;
    private final PsiNameHelper nameHelper;

    @Inject
    public NameValidator(@Nonnull Json2JavaBundle bundle, @Nonnull PsiNameHelper nameHelper) {
        this.bundle = bundle;
        this.nameHelper = nameHelper;
    }

    @Nullable
    @Override
    public String getErrorText(@Nullable String name) {
        if (nameHelper.isQualifiedName(name)) {
            return null;
        }
        return bundle.message("error.message.validator.name.invalid");
    }

    @Override
    public boolean checkInput(@Nullable String name) {
        return true;
    }

    @Override
    @SuppressWarnings("RedundantIfStatement")
    public boolean canClose(@Nullable String name) {
        return nameHelper.isQualifiedName(name);
    }
}
