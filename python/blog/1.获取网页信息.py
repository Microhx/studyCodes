# encoding=utf-8
# 自动阅读文章并添加赞

from urllib.request import urlopen

def read_net(url):
    content = urlopen(url).read().decode("utf-8")
    print(content)
    pass



#url = "https://blog.csdn.net/u013762572/article/details/74781854"
#read_net(url)









