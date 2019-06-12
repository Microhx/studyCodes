### 相关检测方案
1. 对于Android 7.0以上版本可以使用 FrameMetrics检测，具体可参见开源库：<br/>
  https://github.com/frogermcs/ActivityFrameMetrics

2. 检测组件的初始化时间，可以得到非常多的优化。可以参见组件库：<br/>
  https://github.com/InflationX/ViewPump   

### 相关优化建议
#### 布局层级优化
  1. 使用 &lt;merge /&gt; 去除不必要的布局嵌套；
  2. 对于一个 ViewGroup layout 中只有一个字 View 的场景，需要去除外层的  ViewGroup，直接使用子 View。

#### 使用更加高效的布局方式
1. 尽可能避免使用 layout_weight 属性和超过一层的 Linearlayout 嵌套，这样性能很差， AS 会提出警告；
2. 使用 ConstraintLayout 替代 RelativeLayout。


#### 其他建议
1. 能够使用懒加载的，尽可能使用懒加载；
2. 能够进行异步的内容，尽可能使用异步进行；
3. 尽可能对于所有数据库都采用异步操作；
4. APK 的体积对于冷启动也会有影响，尽可能减小 APK 体积
5. 尽可能避免加载大图片，特别是直接把原图 load 到 ImageView 中，这样会明显阻碍初始化过程，导致卡顿。 不可避免的时候，可以考虑使用 Glide 或者缩小采样率和分辨率。
6. 在启动的时候 避免使用大量的反射；反射的 class/Method/Field 应该使用 static 缓存以供复用。

7. 尽可能考虑使用代码进行布局，而非 XML 布局。
