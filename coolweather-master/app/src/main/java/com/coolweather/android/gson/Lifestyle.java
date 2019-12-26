package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;
/*
解析lifestyle数据对应的类
将lifestyle中的type,brf,txt解析出来
HeFeng中lifestyle类型如下：
"lifestyle":[{"type":"comf","brf":"较不舒适","txt":"白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。"},
{"type":"drsg","brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},
{"type":"flu","brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"},
{"type":"sport","brf":"较适宜","txt":"天气较好，无雨水困扰，较适宜进行各种运动，但因天气凉，在户外运动请注意增减衣物。"},
{"type":"trav","brf":"适宜","txt":"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。"},
{"type":"uv","brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"},
{"type":"cw","brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},
{"type":"air","brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响。"}]}
因为lifestyle返回的是一个列表数据，所以在weather类中需定义为List<E>型
 */
public class Lifestyle {

    @SerializedName("type")
    public String type;

    @SerializedName("brf")
    public String brif;


        @SerializedName("txt")
        public String info;

}
