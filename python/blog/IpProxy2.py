# encoding=utf-8
import random
import urllib

import requests
from bs4 import BeautifulSoup
from urllib.request import urlopen

import gevent
import gevent.monkey
gevent.monkey.patch_all() #自动切换


class IpProxy2():
    def __init__(self):
        self.check_url = "https://blog.csdn.net/u013762572/article/details/80031621"

        self.ip_pool = []
        self.ip_pool_after_validate = []
        self.url = "http://www.xicidaili.com/nn/"
        self.headers = {
            "Host": "www.xicidaili.com",
            "User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36",
            "Upgrade-Insecure-Requests": "1"
        }

        self.pcUserAgent = [
            "User-Agent:Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
            "User-Agent:Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
            "User-Agent:Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0);",
            "User-Agent:Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)",
            "User-Agent:Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)",
            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:2.0.1) Gecko/20100101 Firefox/4.0.1",
            "User-Agent:Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1",
            "User-Agent:Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; en) Presto/2.8.131 Version/11.11",
            "User-Agent:Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; TencentTraveler 4.0)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; The World)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SE 2.X MetaSr 1.0; SE 2.X MetaSr 1.0; .NET CLR 2.0.50727; SE 2.X MetaSr 1.0)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Avant Browser)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"
        ]

    def get_ip(self):
        try:
            result = requests.get(self.url, headers=self.headers)
            # result = urlopen(self.url).read().decode("utf-8")
            print("result:")
            print(result)

        except Exception as e:
            print("获取代理失败", e)
            raise

        result.encoding="utf-8"
        content = result.content

        bs = BeautifulSoup(content, "html.parser")
        trs = bs.find_all("tr")
        if trs is not None and len(trs) > 0:
            for tr in trs:
                try:
                    ip = {}
                    tds = tr.find_all("td")
                    ip["address"] = tds[1].text
                    ip["port"] = tds[2].text
                    ip["type"] = tds[5].text.lower()

                    self.ip_pool.append(ip)

                except Exception as e:
                    print("解析出现异常", e)

    def validate_proxy3(self, item_pool):
        if item_pool is not None and len(item_pool) > 0:
            task_item = []
            for item in item_pool:
                task_item.append(gevent.spawn(self.validate_proxy,item))
            gevent.joinall(task_item)

    def validate_proxy2(self):
        task_list = []
        for item in self.ip_pool:  # 根据urllist,新建一个协程组，自动切换
            task_list.append(gevent.spawn(self.validate_proxy, item))
        gevent.joinall(task_list)

    def validate_proxy(self,item):
        proxies = {
            "http": "",
            "https": ""
        }

        ip = item["type"] + "://" + item["address"] + ":" + item["port"]
        if item["type"] == "http":
           proxies["https"] = ""
           proxies["http"] = ip
           try:
              res = self.create_proxy("http", item["address"] + ":" + item["port"])
              if res is True:
                  print(ip + " : ip可用")
                  self.ip_pool_after_validate.append(ip)
           except:
                print(ip + " : 是不可用代理")

        else:
           proxies["http"] = ""
           proxies["https"] = ip
           try:
              res = self.create_proxy("https", item["address"] + ":" + item["port"])
              if res is True:
                  print(ip + ": ip可用")
                  self.ip_pool_after_validate.append(ip)
           except:
                 print(ip + ":是不可用代理")

    # 创建代理
    def create_proxy(self, type_key, address):
        proxy_support = urllib.request.ProxyHandler({type_key: address})
        opener = urllib.request.build_opener(proxy_support, urllib.request.HTTPHandler)
        urllib.request.install_opener(opener)

        req = urllib.request.Request(self.check_url)
        req.add_header("User-Agent", self.pcUserAgent[random.randint(0, len(self.pcUserAgent))])

        try:
            content = urllib.request.urlopen(req).read().decode("utf-8")
            # print(content)
            return True
        except Exception as e:
            print("代理异常", e)
            return False