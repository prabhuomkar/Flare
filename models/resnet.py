"""ResNet-18"""
import torch
import torchvision


# laod alexnet model
model = torchvision.models.resnet18(pretrained=True)
model.eval()
# example tensor as input
example = torch.rand(1, 3, 224, 224)
# create torchscript serialized pytorch pretrained model
traced_script_module = torch.jit.trace(model, example)
traced_script_module.save("output/resnet_18.pt")
