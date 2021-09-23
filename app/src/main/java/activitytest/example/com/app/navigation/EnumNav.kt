package activitytest.example.com.app.navigation

enum class EnumNav : ChangeName {
    HOME {
        private var moduleName:String? = "主页"

        override fun changeName(string: String?) {
            moduleName = string
        }

        override fun getNavName(): String {
           return moduleName ?: ""
        }

        override fun toString(): String {
            return moduleName ?: ""
        }
    },
    LOG {
        private var moduleName:String? = "日志"

        override fun changeName(string: String?) {
            moduleName = string
        }

        override fun getNavName(): String {
            return moduleName ?: ""
        }

        override fun toString(): String {
            return moduleName ?: ""
        }
    },
    LABEL {
        private var moduleName:String? = "标签"
        override fun changeName(string: String?) {
            moduleName = string
        }

        override fun getNavName(): String {
            return moduleName ?: ""
        }

        override fun toString(): String {
            return moduleName ?: ""
        }
    },
    MANAGE {
        private var moduleName:String? = "我的"
        override fun changeName(string: String?) {
            moduleName = string
        }

        override fun getNavName(): String {
            return moduleName ?: ""
        }

        override fun toString(): String {
            return moduleName ?: ""
        }
    },
    CONTENT {
        private var moduleName:String? = "内容"
        override fun changeName(string: String?) {
            moduleName = string
        }

        override fun getNavName(): String {
            return moduleName ?: ""
        }

        override fun toString(): String {
            return moduleName ?: ""
        }
    };

}