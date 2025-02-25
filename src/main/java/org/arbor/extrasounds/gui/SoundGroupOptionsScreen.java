/*
 * Copyright 2021 stashymane
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.arbor.extrasounds.gui;

import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import org.arbor.extrasounds.sounds.SoundSouceInit;

import java.util.Arrays;

public class SoundGroupOptionsScreen extends AbstractSoundListedScreen {
    private final SoundSource parentCategory;

    public SoundGroupOptionsScreen(Screen parent, Options gameOptions, SoundSource category) {
        super(parent, gameOptions, Component.translatable("soundCategory." + category.getName()));
        parentCategory = category;
    }

    protected void init() {
        this.list = new SoundList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);

        this.list.addCategory(parentCategory);

        final SoundSource[] categories = Arrays.stream(SoundSource.values()).filter(it ->
                SoundSouceInit.PARENTS.containsKey(it) && SoundSouceInit.PARENTS.get(it) == parentCategory && it != parentCategory).toArray(SoundSource[]::new);
        this.list.addAllCategory(categories);

        this.addWidget(this.list);

        this.addDoneButton();
    }
}
