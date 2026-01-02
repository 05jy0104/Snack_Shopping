$snacks = @(
    @{name="混合坚果";categoryId=1;categoryName="坚果类";price=88.00;image="https://images.unsplash.com/photo-1606787366850-de6330128bfc?w=400";description="多种坚果混合搭配，营养均衡";stock=120;sales=80},
    @{name="巴旦木";categoryId=1;categoryName="坚果类";price=45.00;image="https://images.unsplash.com/photo-1558160074-4d7d8bdf4256?w=400";description="加州巴旦木，颗粒饱满，口感香脆";stock=90;sales=40},
    @{name="夏威夷果";categoryId=1;categoryName="坚果类";price=78.00;image="https://images.unsplash.com/photo-1563805042-7684c019e1cb?w=400";description="澳洲进口夏威夷果，奶香浓郁";stock=70;sales=55},
    @{name="水果软糖";categoryId=2;categoryName="糖果类";price=18.00;image="https://images.unsplash.com/photo-1582053433976-25c00369fc93?w=400";description="多种水果口味，软糯Q弹";stock=200;sales=120},
    @{name="牛奶糖";categoryId=2;categoryName="糖果类";price=15.00;image="https://images.unsplash.com/photo-1558160074-4d7d8bdf4256?w=400";description="浓郁奶香，香甜可口";stock=180;sales=90},
    @{name="薄荷糖";categoryId=2;categoryName="糖果类";price=12.00;image="https://images.unsplash.com/photo-1582053433976-25c00369fc93?w=400";description="清新薄荷，提神醒脑";stock=150;sales=70},
    @{name="巧克力豆";categoryId=2;categoryName="糖果类";price=25.00;image="https://images.unsplash.com/photo-1549007994-cb92caebd54b?w=400";description="进口可可豆制作，口感醇厚";stock=160;sales=85},
    @{name="彩虹糖";categoryId=2;categoryName="糖果类";price=20.00;image="https://images.unsplash.com/photo-1582053433976-25c00369fc93?w=400";description="五颜六色，酸甜可口";stock=190;sales=110},
    @{name="薯片";categoryId=3;categoryName="膨化食品";price=12.00;image="https://images.unsplash.com/photo-1566478989037-eec170784d0b?w=400";description="经典原味薯片，薄脆爽口";stock=250;sales=180},
    @{name="爆米花";categoryId=3;categoryName="膨化食品";price=15.00;image="https://images.unsplash.com/photo-1578849278619-e73505e9610f?w=400";description="焦糖爆米花，香甜酥脆";stock=200;sales=140},
    @{name="虾条";categoryId=3;categoryName="膨化食品";price=10.00;image="https://images.unsplash.com/photo-1566478989037-eec170784d0b?w=400";description="鲜虾味虾条，口感酥脆";stock=220;sales=160},
    @{name="玉米片";categoryId=3;categoryName="膨化食品";price=14.00;image="https://images.unsplash.com/photo-1566478989037-eec170784d0b?w=400";description="玉米制作，香脆可口";stock=180;sales=130},
    @{name="洋葱圈";categoryId=3;categoryName="膨化食品";price=11.00;image="https://images.unsplash.com/photo-1566478989037-eec170784d0b?w=400";description="洋葱味浓郁，口感独特";stock=170;sales=120},
    @{name="巧克力饼干";categoryId=4;categoryName="饼干类";price=22.00;image="https://images.unsplash.com/photo-1499636136210-6f4ee915583e?w=400";description="巧克力夹心饼干，香甜美味";stock=200;sales=150},
    @{name="黄油曲奇";categoryId=4;categoryName="饼干类";price=28.00;image="https://images.unsplash.com/photo-1499636136210-6f4ee915583e?w=400";description="纯正黄油制作，酥脆香甜";stock=150;sales=100},
    @{name="苏打饼干";categoryId=4;categoryName="饼干类";price=18.00;image="https://images.unsplash.com/photo-1499636136210-6f4ee915583e?w=400";description="低糖低脂，健康美味";stock=180;sales=120},
    @{name="全麦饼干";categoryId=4;categoryName="饼干类";price=20.00;image="https://images.unsplash.com/photo-1499636136210-6f4ee915583e?w=400";description="全麦制作，营养丰富";stock=160;sales=110},
    @{name="夹心饼干";categoryId=4;categoryName="饼干类";price=16.00;image="https://images.unsplash.com/photo-1499636136210-6f4ee915583e?w=400";description="多种口味夹心，口感丰富";stock=190;sales=140},
    @{name="芒果干";categoryId=5;categoryName="果脯类";price=35.00;image="https://images.unsplash.com/photo-1553279768-865429fa0078?w=400";description="新鲜芒果制作，酸甜可口";stock=120;sales=80},
    @{name="葡萄干";categoryId=5;categoryName="果脯类";price=28.00;image="https://images.unsplash.com/photo-1596363505729-4190a9506133?w=400";description="优质葡萄干，甜而不腻";stock=150;sales=100},
    @{name="山楂片";categoryId=5;categoryName="果脯类";price=22.00;image="https://images.unsplash.com/photo-1553279768-865429fa0078?w=400";description="开胃消食，酸甜可口";stock=140;sales=90},
    @{name="话梅";categoryId=5;categoryName="果脯类";price=25.00;image="https://images.unsplash.com/photo-1553279768-865429fa0078?w=400";description="经典话梅，回味悠长";stock=130;sales=85},
    @{name="杏脯";categoryId=5;categoryName="果脯类";price=30.00;image="https://images.unsplash.com/photo-1553279768-865429fa0078?w=400";description="新鲜杏子制作，香甜可口";stock=110;sales=75}
)

foreach ($snack in $snacks) {
    $body = $snack | ConvertTo-Json
    $result = Invoke-RestMethod -Uri "http://localhost:8080/api/admin/snack/add" -Method Post -ContentType "application/json" -Body $body
    Write-Host "添加 $($snack.name): $($result.success)"
}
