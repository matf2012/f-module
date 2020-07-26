# file-scan 文件扫描
	项目是扫描指定文件夹，可以通过调用的方式使用，也可以通过运行FileScanMain类运行
## 使用:

### FileScanMain类
	运行扫描可以通过两种方式：
	参数 ：参数１是扫描路径，参数２是输出位置【１控制台，２文件】，参数３是输出文件路径（参数２输入２时有用）
	控制台输入：根据控制台提示输入
	
### FileScanCore类
	代码示例：
	public void test(){
		PropertyBuilder builder = new PropertyBuilder()
                .parallel(FileScanConstant.SCAN_FILE_PARALLEL_TRUE)
                .outType(FileScanConstant.SCAN_FILE_OUTER_CONSOLE)
                .basePath(project.getComparePath());
        FileScanCore core = new FileScanCore(builder.build());
        final List<ScanFile> scanList = new ArrayList<ScanFile>();
        ListenerManager.register(new DefaultListener(){
            @Override
            public boolean afterScan(FileScanProperty property, List<ScanFile> list) {
                scanList.addAll(list);
                return super.afterScan(property, list);
            }
        });
        ListenerManager.register(new OuterListener());
        core.scan();
	}
	Builder.parallel 配置并发扫描
	Builder.basePath 扫描路径
	ListenerManager.register 注册自定义监听器，用于处理扫描完成的结果
	core.scan 执行扫描
	
## 介绍

### 配置属性
	属性类：FileScanProperty
	构造器：PropertyBuilder
	属性包括，扫描路径，输出模式（OuterListener 属性）、是否并发、是否排序
	
### 监听
	控制类：ListenerManager
	可选事件：BEFORE_SCAN_PATH,AFTER_SCAN_PATH,BEFORE_SCAN,AFTER_SCAN,INIT,ERROR
	可以通过实现接口：IFileScanListener 或 继承DefaultListener 实现指定方法创建监听类，然后通过ListenerManager.register方法进行注册
	提供监听类：
		MonitorListener 运行监控，并发时可以检查扫描完成，系统默认已注册
		SortListener 排序，有四种排序模式：SIZE_ASC,SIZE_DESC,COUNT_ASC,COUNT_DESC
		OuterListener 输出：可输出控制台或指定文件
		ErrorListener
		
### 主要功能
	FileScanCore类：功能主要类，通过创建FileScanCore对象，调用sacn方法进行扫描，负责控制扫描和触发监听事件。
	SimpleFileScanner ： 扫描类
	ParallelFileScanner ： 并发扫描类
	
