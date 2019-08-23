# utils
实战项目分享
===========================================

GitHub中的README文件，使用markdown格式的文件。这种文件语法比较简单，特别容易上手。

Markdown的语法简洁明了、学习容易，而且功能比纯文本更强，因此有很多人用它写博客。世界上最流行的博客平台WordPress和大型CMS如joomla、drupal都能很好的支持Markdown。

下面简单介绍下相关语法：

大标题：

===================================

大标题一般显示工程名,你只要在标题下面跟上=====即可（“=”个数无限制）

中标题：

———————————-

中标题一般显示重点项,你只要在标题下面输入——即可（“-”个数无限制）

小标题：

小标题的格式如下 ### 小标题，注意#和标题字符中间要有空格

也可以使用1到6个“#”来表示六级标题：

# 这是 H1

## 这是 H2

###### 这是 H6

斜体：

可以使用* 或 _ 包围文字，例如*Hello World*

加粗：

用两个 * 或 _ 包起来的话，达到加粗字体的左右，例如**Hello Markdown**

如果你的 * 和 _ 两边都有空白的话，它们就只会被当成普通的符号

单行文本框：

只要两个Tab再输入文字即可

多行文本框：

你可以写入代码等,每行文字只要输入两个Tab再输入文字即可

引用：

引用是使用类似 email 中用 > 的引用方式，例如：> 文字被些字符包围开始，可以在每行上加上“<”，也可以只在首行加上“<”能够达到一样的效果。区块引用可以嵌套（例如：引用内的引用），只要根据层次加上不同数量的 > ：

> 这是第一层引用。

>

> > 这是嵌套的引用块。

>

> 回到第一层。

列表：

无序列表使用星号、加号或是减号作为列表标记，例如：*Red、+Green、-Blue

有序列表则使用数字接着一个英文句点，例如：1.hello；注意，你在列表标记上使用的数字并不会影响输出的 HTML 结果，你可以使用1. Bird 1. McHale 1. Parish甚至6.ADC 3.EFG 9.XYZ 都可以实现正确的有序列表显示。

超链接：

Markdown 支持两种形式的链接语法： 行内式和参考式两种形式。

不管是哪一种，链接文字都是用 [方括号] 来标记；要建立一个行内式的链接，只要在方块括号后面紧接着圆括号并插入网址链接即可，如果你还想要加上链接的 title 文字，只要在网址后面，用双引号把 title 文字包起来即可，例如：[This link](http://example.net/) has no title attribute

如果你是要链接到同样主机的资源，你可以使用相对路径：See my [About](/about/) page for details

参考式的链接是在链接文字的括号后面再接上另一个方括号，而在第二个方括号里面要填入用以辨识链接的标记：This is [an example][id] reference-style link

接着，在文件的任意处，你可以把这个标记的链接内容定义出来：[id]: http://example.com/ "Optional Title Here"

图片：

图片的语法和超链接非常相似。只显示图片：![github](http://example.com/unicorn.png “github”)；点击图片进入另一个网页：[![image]](http://www.example.com/) [image]: http://example.com/example.png “example“

点击图片添加微信:
![image](wx.jpg)
![image](https://img-blog.csdnimg.cn/20190823161421856.JPG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVfbW9vbjgyMQ==,size_16,color_FFFFFF,t_70)

