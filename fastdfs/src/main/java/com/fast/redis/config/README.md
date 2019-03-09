@EnableCaching开启全局缓存
#Cache全局缓存
args
cacheNames===value  ===>选择其中一个即可  表示缓存的名字
key===keyGenerator 选择其中一个即可主键Id,唯一
cacheManager===cacheResolver 缓存管理器和解析器.指定一个即可
condition===>(使用SqEL表达式)满足条件才会被缓存
unless===>(使用Sqel表达式)条件为true才会被缓存
sync====>异步和unless不能共同使用

Cache
