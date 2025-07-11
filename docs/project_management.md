# プロジェクト管理ガイド / Project Management Guide

## 作業規模とタイムラインの定義 / Work Scale and Timeline Definition

プロジェクトの作業規模を以下のタグで分類し、必要時間を明確に定義します：
Classify project work scale with the following tags and clearly define required time:

### Tag management

#### Regarding the task size

| タグ / Tag  | 必要時間 / Required Time | 説明 / Description                                                     |
|-----------|----------------------|----------------------------------------------------------------------|
| 🟢 **XS** | 4 hours              | 小さなバグ修正、軽微な機能追加 / Small bug fixes, minor feature additions           |
| 🟣 **S**  | 8 hours              | 中程度のバグ修正、小機能の実装 / Medium bug fixes, small feature implementation     |
| 🟡 **M**  | 16 hours             | 新機能の実装、複数ファイルにわたる変更 / New feature implementation, multi-file changes |
| 🟠 **L**  | 32 hours             | 大きな機能追加、システム設計変更 / Major feature additions, system design changes    |
| 🔴 **XL** | 64 hours             | メジャーリリース、アーキテクチャ変更 / Major releases, architecture changes            |

### 📋 プロジェクト計画の原則 / Project Planning Principles

- Optimistic & Pessimistic timeline management. 

# Branch management
- since the Copilot only able to check out the canary branch, therefore we regard the canary branch as the default branch and with the most drastic changes.
  - This allows anyone to use the canary branch to assign small tasks within ongoing feature development.
  - However, the changes from the canary branch should not be merged to the feature development branch for the sake that you might merge commits from other feature branches. Therefore, create the patches showing the differences between the branch created by the copilot and the canary branch is recommended.
- Normally we create PR on the main branch, and then merge it to the canary branch.