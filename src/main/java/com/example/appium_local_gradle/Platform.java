package com.example.appium_local_gradle;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Platform {

        Generic(1, "Testsigma", "Testsigma Lab"),
        Windows(2, "Windows", "Windows"),
        Mac(3, "OS X", "Mac OS"),
        Linux(4, "Linux", "Linux"),
        Android(5, "Android", "Android"),
        iOS(6, "iOS", "iOS");
        private Integer id;
        private String os;
        private String displayName;

        public static Platform getPlatform(Integer id) {

            switch (id) {
                case 1:
                    return Generic;
                case 2:
                    return Windows;
                case 3:
                    return Mac;
                case 4:
                    return Linux;
                case 5:
                    return Android;
                case 6:
                    return iOS;
            }
            return null;
        }

}
