package com.xing.workmanagerdome.view

import com.xing.workmanagerdome.view.ScrollNumberView.Companion.NO_VALUE

class NumberUnit (var currentValue: Int = NO_VALUE,
                  var nextValue: Int = NO_VALUE) {

        override fun toString(): String {
            return "NumberUnit(currentValue=$currentValue, nextValue=$nextValue)"
        }
    }