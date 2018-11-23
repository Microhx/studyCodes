# encoding=utf-8
import requests
from bs4 import BeautifulSoup

class IpProxy():
    def __init__(self):
        self.check_url = "https://blog.csdn.net/u013762572/article/details/79648159"

        self.ip_pool = []
        self.ip_pool_after_validate = []
        self.url = "http://www.xicidaili.com/nn/"
        self.headers = {
            "Host": "www.xicidaili.com",
            "User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36",
            "Upgrade-Insecure-Requests": "1"
        }

    def get_ip(self):

        try:
            result = requests.get(self.url, headers=self.headers)
            print("result:")
            print(result)

        except Exception as e:
            print("获取代理失败",e)
            raise

        result.encoding="utf-8"
        content = result.content

        bs = BeautifulSoup(content,"html.parser")
        trs = bs.find_all("tr")
        if trs != None and len(trs) > 0 :
            for tr in trs:
                try:
                    ip ={}
                    tds = tr.find_all("td")
                    ip["address"] = tds[1].text
                    ip["port"] = tds[2].text
                    ip["type"] = tds[5].text.lower()

                    self.ip_pool.append(ip)

                except Exception as e:
                    print("解析出现异常",e)

    def validate_proxy(self):
        proxies = {
            "http":"",
            "https":""
        }

        for item in self.ip_pool:
            ip = item["type"] + "://" + item["address"] + ":" + item["port"]
            if item["type"] == "http":
                proxies["https"] = ""
                proxies["http"] = ip
                try :
                    res = requests.get(self.check_url,proxies=proxies)
                    if res is None:
                        continue
                    else :
                        print(ip + " : ip可用")
                        self.ip_pool_after_validate.append(ip)
                except :
                    print(ip + " : 是不可用代理")

            else :
                proxies["http"] = ""
                proxies["https"] = ip
                try :
                    res = requests.get(self.check_url, proxies=proxies)
                    if res is None:
                        continue
                    else :
                        print(ip + ": ip可用")
                        self.ip_pool_after_validate.append(ip)

                except :
                    print(ip + ":是不可用代理")
