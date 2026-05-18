#pragma once
#include <string>
#include <vector>

class FlowersBouquet;
class Grower;

class Wholesaler {
public:
	Wholesaler(Grower* grower, std::string name);
	FlowersBouquet* acceptOrder(std::vector<std::string> flowers);
private:
	Grower* grower;
    std::string name;
};
