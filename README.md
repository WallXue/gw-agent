# Great Wall Agent 
  <small>_-利用netty实现的RPC服务中间代理层_</small>
  
  ### gw-agent
        独立运行代理层，利用etcd做注册中心保持所有agent正常连接
        
  ### gw-agent-support
        应用层集成，对接本地运行agent实现远程RPC调用
  
  #
  <small>
  service <-> agent <-> agent <-> service
  
  service与agent，agent与agent之间都采用NIO单一长链接方式，并且支持双向开工
  
  
  </small>
