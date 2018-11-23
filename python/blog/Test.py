# encoding=utf-8
from blog.IpProxy import IpProxy

# proxy = IpProxy()
# proxy.get_ip()
# print(proxy.ip_pool)
# print ("代理池数目" , len(proxy.ip_pool))

# proxy.validate_proxy()

from blog.IpProxy2 import IpProxy2
from blog.KuaiDailiProxy import KuaiDailiProxy

# http://

proxy = IpProxy2()
#proxy.get_ip()
#print(proxy.ip_pool)
#print ("代理池数目" , len(proxy.ip_pool))

kuaiDaili = KuaiDailiProxy()
ip_pool = kuaiDaili.ip_pool

proxy.validate_proxy3(ip_pool)

# proxy.create_proxy("https", "113.108.242.36:47713")




