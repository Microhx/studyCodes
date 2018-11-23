# encoding=utf-8

from urllib.request import urlopen
from bs4 import BeautifulSoup


class KuaiDailiProxy:
    def __init__(self):
        self.ip_url = "https://www.kuaidaili.com/free/intr/4"
        self.ip_pool = []

        self.get_ip_pool()

    def get_ip_pool(self):
        content = urlopen(url=self.ip_url).read().decode("utf-8")
        print(content)

        self.parse_data(content)

    def parse_data(self,data):
        bs = BeautifulSoup(data,"html.parser")

        trs = bs.find_all("tr")
        if trs is not None and len(trs) > 0:
            for tr in trs:
                try:
                    ip = {}
                    tds = tr.find_all("td")
                    ip["port"] = tds[1].text

                    if ip["port"].isdigit():
                        ip["address"] = tds[0].text
                        ip["type"] = tds[3].text.lower()

                        self.ip_pool.append(ip)
                except Exception as e:
                    print("解析出现异常",e)


#content = ProxyContent()
#print(content.ip_pool)




